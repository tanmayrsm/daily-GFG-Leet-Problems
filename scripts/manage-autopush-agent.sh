#!/bin/sh

set -eu

REPO_DIR="${REPO_DIR:-/Users/tamishra/Documents/dlyPrb}"
AGENT_NAME="${AGENT_NAME:-com.tanmayrsm.dlyprb.autopush}"
SOURCE_PLIST="${SOURCE_PLIST:-$REPO_DIR/launchd/$AGENT_NAME.plist}"
TARGET_PLIST="${TARGET_PLIST:-$HOME/Library/LaunchAgents/$AGENT_NAME.plist}"
STDOUT_LOG="${STDOUT_LOG:-/tmp/dlyprb-autopush.out.log}"
STDERR_LOG="${STDERR_LOG:-/tmp/dlyprb-autopush.err.log}"

usage() {
  cat <<'EOF'
Usage:
  manage-autopush-agent.sh status
  manage-autopush-agent.sh install
  manage-autopush-agent.sh reinstall
  manage-autopush-agent.sh print

Commands:
  status     Show whether the installed LaunchAgent matches the repo and print recent errors.
  install    Copy the repo plist into ~/Library/LaunchAgents and bootstrap it if needed.
  reinstall  Force-copy the repo plist and reload the LaunchAgent.
  print      Print the active launchctl record for the agent.
EOF
}

agent_id() {
  printf 'gui/%s/%s' "$(id -u)" "$AGENT_NAME"
}

ensure_source_exists() {
  if [ ! -f "$SOURCE_PLIST" ]; then
    printf 'Source plist not found: %s\n' "$SOURCE_PLIST" >&2
    exit 1
  fi
}

plist_hash() {
  shasum -a 256 "$1" | awk '{print $1}'
}

show_recent_errors() {
  if [ -f "$STDERR_LOG" ]; then
    printf 'Recent stderr (%s):\n' "$STDERR_LOG"
    tail -n 10 "$STDERR_LOG"
  else
    printf 'No stderr log found at %s\n' "$STDERR_LOG"
  fi
}

print_launchctl_summary() {
  awk '
    /resource coalition =/ { exit }
    /state =/ || /last exit code =/ || /runs =/ || /stdout path =/ || /stderr path =/ || /program =/ || /arguments =/ {
      print
    }
  ' "$1"
}

status() {
  ensure_source_exists

  printf 'Source plist: %s\n' "$SOURCE_PLIST"
  if [ -f "$TARGET_PLIST" ]; then
    printf 'Installed plist: %s\n' "$TARGET_PLIST"
    source_hash="$(plist_hash "$SOURCE_PLIST")"
    target_hash="$(plist_hash "$TARGET_PLIST")"
    printf 'Repo hash:      %s\n' "$source_hash"
    printf 'Installed hash: %s\n' "$target_hash"
    if [ "$source_hash" = "$target_hash" ]; then
      printf 'Status: installed plist matches repo.\n'
    else
      printf 'Status: installed plist is stale and should be reloaded.\n'
    fi
  else
    printf 'Installed plist missing: %s\n' "$TARGET_PLIST"
  fi

  if launchctl print "$(agent_id)" >/tmp/dlyprb-launchctl-print.tmp 2>/dev/null; then
    print_launchctl_summary /tmp/dlyprb-launchctl-print.tmp
    rm -f /tmp/dlyprb-launchctl-print.tmp
  else
    printf 'Launchctl: agent is not currently loaded.\n'
  fi

  show_recent_errors
}

install_or_reinstall() {
  mode="$1"
  ensure_source_exists

  mkdir -p "$(dirname "$TARGET_PLIST")"
  if [ "$mode" = "install" ] && [ -f "$TARGET_PLIST" ] && [ "$(plist_hash "$SOURCE_PLIST")" = "$(plist_hash "$TARGET_PLIST")" ]; then
    printf 'Installed plist already matches repo. Reloading LaunchAgent.\n'
  else
    cp "$SOURCE_PLIST" "$TARGET_PLIST"
    chmod 644 "$TARGET_PLIST"
    printf 'Copied %s to %s\n' "$SOURCE_PLIST" "$TARGET_PLIST"
  fi

  launchctl bootout "$(agent_id)" >/dev/null 2>&1 || true
  launchctl bootstrap "gui/$(id -u)" "$TARGET_PLIST"
  printf 'Reloaded %s\n' "$AGENT_NAME"
  launchctl print "$(agent_id)" >/tmp/dlyprb-launchctl-print.tmp
  print_launchctl_summary /tmp/dlyprb-launchctl-print.tmp
  rm -f /tmp/dlyprb-launchctl-print.tmp
}

print_agent() {
  launchctl print "$(agent_id)"
}

command="${1:-status}"

case "$command" in
  status)
    status
    ;;
  install)
    install_or_reinstall install
    ;;
  reinstall)
    install_or_reinstall reinstall
    ;;
  print)
    print_agent
    ;;
  *)
    usage >&2
    exit 1
    ;;
esac
