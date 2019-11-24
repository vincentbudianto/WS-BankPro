chmod 400 $pem_key
ssh -o "StrictHostKeyChecking=no" -i $pem_key $machine@$IP "rm -rf ws-bankpro && mkdir ws-bankpro"
scp -r -o "StrictHostKeyChecking=no" -i $pem_key * $machine@$IP:~/ws-bankpro
ssh -o "StrictHostKeyChecking=no" -i $pem_key $machine@$IP "bash" < ./run.sh
