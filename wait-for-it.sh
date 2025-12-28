#!/usr/bin/env bash
# wait-for-it.sh - https://github.com/vishnubob/wait-for-it
# Minimal safe subset
set -e
host="$1"
shift
cmd=("$@")
if [ -z "$host" ]; then
  echo "Usage: $0 host:port -- command"
  exit 1
fi
# Split host:port
IFS=':' read -r host_only port <<<"$host"

wait_for() {
  nc -z "$host_only" "$port"
}

until wait_for; do
  >&2 echo "Waiting for $host to be available..."
  sleep 2
done
>&2 echo "$host is available - executing command"
exec "${cmd[@]}"
