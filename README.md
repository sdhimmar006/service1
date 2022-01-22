Steps to run in GCP shell:

1. git clone https://github.com/sdhimmar006/service1.git
2. git clone https://github.com/sdhimmar006/service2.git
3. chmod +x service1/mvnw
4. chmod +x service2/mvnw
5. gcloud services enable cloudtrace.googleapis.com
6. gcloud iam service-accounts create testtrace
7. export PROJECT_ID=$(gcloud config list --format 'value(core.project)')
8. gcloud projects add-iam-policy-binding ${PROJECT_ID} --member serviceAccount:testtrace@${PROJECT_ID}.iam.gserviceaccount.com --role roles/editor
9. gcloud iam service-accounts keys create ~/service-account.json --iam-account testtrace@${PROJECT_ID}.iam.gserviceaccount.com
10. cd service1
11. ./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=cloud -Dspring.cloud.gcp.credentials.location=file:///$HOME/service-account.json"
12. open new tab    
13. cd ../service2
14. ./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=cloud -Dspring.cloud.gcp.credentials.location=file:///$HOME/service-account.json"
    open new tab
15. curl http://localhost:8081/service1