cd ws-bankpro
mvn package
sudo cp target/web_service_bank_pro.war /opt/tomcat/latest/webapps
sudo systemctl restart tomcat