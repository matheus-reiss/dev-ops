cd Ci-Cd
sudo apt-get update
sudo apt-get install -y mailutils
echo "Sending e-mail after pipeline completion" | mail -s "Pipeline" reismatheus514@gmail.com