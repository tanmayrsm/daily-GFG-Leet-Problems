#!/bin/sh

set -eu

REPO_DIR="${REPO_DIR:-/Users/tamishra/Documents/dlyPrb}"
BRANCH="${BRANCH:-main}"
IST_TZ="${IST_TZ:-Asia/Kolkata}"
RUN_WINDOW_HOUR="${RUN_WINDOW_HOUR:-12}"
TOKEN_FILE="${GITHUB_TOKEN_FILE:-$HOME/.config/dlyPrb/github_token}"
STATE_DIR="${STATE_DIR:-$HOME/.local/state/dlyPrb-autopush}"
STAMP_FILE="$STATE_DIR/last_run_ist_date"
LOCK_DIR="${LOCK_DIR:-/tmp/dlyprb-autopush.lock}"
PATH="/usr/bin:/bin:/usr/sbin:/sbin:/opt/homebrew/bin:/usr/local/bin"

log() {
  printf '%s %s\n' "$(date '+%Y-%m-%d %H:%M:%S %Z')" "$*"
}

cleanup() {
  rmdir "$LOCK_DIR" 2>/dev/null || true
}

if ! mkdir "$LOCK_DIR" 2>/dev/null; then
  log "Another auto-push run is already in progress; skipping."
  exit 0
fi
trap cleanup EXIT INT TERM

mkdir -p "$STATE_DIR"

current_ist_date="$(TZ="$IST_TZ" date '+%Y-%m-%d')"
current_ist_hour="$(TZ="$IST_TZ" date '+%H')"

if [ "$current_ist_hour" != "$(printf '%02d' "$RUN_WINDOW_HOUR")" ]; then
  log "Outside IST push window; current IST hour is $current_ist_hour."
  exit 0
fi

if [ -f "$STAMP_FILE" ] && [ "$(cat "$STAMP_FILE")" = "$current_ist_date" ]; then
  log "Push already attempted for $current_ist_date IST."
  exit 0
fi

if [ ! -r "$TOKEN_FILE" ]; then
  log "Token file not found or unreadable at $TOKEN_FILE."
  exit 1
fi

if [ ! -d "$REPO_DIR/.git" ]; then
  log "Git repo not found at $REPO_DIR."
  exit 1
fi
 
cd "$REPO_DIR"

if [ -f .git/MERGE_HEAD ] || [ -d .git/rebase-merge ] || [ -d .git/rebase-apply ] || [ -f .git/CHERRY_PICK_HEAD ]; then
  log "Git operation in progress; skipping auto-push."
  exit 1
fi

git checkout "$BRANCH"

git add -A

if ! git diff --cached --quiet; then
  git commit -m "chore: daily auto-push $(TZ="$IST_TZ" date '+%Y-%m-%d %H:%M IST')"
else
  log "No local file changes to commit."
fi

if ! git rev-list --left-right --count "origin/$BRANCH...$BRANCH" >/dev/null 2>&1; then
  log "Remote branch origin/$BRANCH not available; attempting push anyway."
fi

export GITHUB_USERNAME="${GITHUB_USERNAME:-tanmayrsm}"
export GITHUB_TOKEN_FILE="$TOKEN_FILE"
export GIT_TERMINAL_PROMPT=0

git -c credential.helper= -c core.askPass="$REPO_DIR/scripts/github-askpass.sh" push origin "$BRANCH"

printf '%s\n' "$current_ist_date" > "$STAMP_FILE"
log "Auto-push completed successfully."
