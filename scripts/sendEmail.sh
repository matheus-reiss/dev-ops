set -euxo pipefail

TO="${NOTIFY_TO_EMAIL:?Missing NOTIFY_TO_EMAIL env var}"

SUBJECT="${EMAIL_SUBJECT:-Pipeline}"
BODY=$(cat <<EOF
Pipeline finished.
Tests: ${STATUS_TESTS:-unknown}
Build: ${STATUS_BUILD:-unknown}
Run: ${GITHUB_RUN_URL:-n/a}
EOF
)

echo -e "$BODY" | mail -s "$SUBJECT" "$TO"