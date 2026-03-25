#!/bin/sh

token_file="${GITHUB_TOKEN_FILE:-$HOME/.config/dlyPrb/github_token}"

case "$1" in
  *Username*)
    printf '%s\n' "${GITHUB_USERNAME:-tanmayrsm}"
    ;;
  *Password*)
    if [ -r "$token_file" ]; then
      cat "$token_file"
    else
      printf '\n'
    fi
    ;;
  *)
    printf '\n'
    ;;
esac
