set -euxo pipefail

echo "Sending e-mail after pipeline completion" \
  | mail -s "Pipeline" reismatheus514@gmail.com