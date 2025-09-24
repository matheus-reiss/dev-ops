cd Ci-Cd
sudo api-get update
sudo api-get install -y mailutils
echo "Sending e-mail after pipeline completion" | mail -s "Pipeline" reismatheus514@gmail.com