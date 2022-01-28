
**One time setup**

```
git clone https://github.com/sdhimmar006/service1.git
chmod +x service1/mvnw
export SERVICE1_PORT=8081
export APP_NAME=service1
export GOOGLE_CLOUD_PROJECT=`gcloud config list --format="value(core.project)"`
export VERSION=$(($(date +%s%N)/1000000))
export baseurl=
cd service1
./mvnw -DskipTests package
./mvnw -DskipTests com.google.cloud.tools:jib-maven-plugin:build \
-Dimage=gcr.io/$GOOGLE_CLOUD_PROJECT/$APP_NAME:$VERSION
kubectl create deployment $APP_NAME \
--image=gcr.io/$GOOGLE_CLOUD_PROJECT/$APP_NAME:$VERSION
kubectl create service loadbalancer $APP_NAME --tcp=$SERVICE1_PORT:$SERVICE1_PORT
```

**Update an existing service**
```
export VERSION=v2
export APP_NAME=service1
./mvnw -DskipTests package
export GOOGLE_CLOUD_PROJECT=`gcloud config list --format="value(core.project)"`
./mvnw -DskipTests com.google.cloud.tools:jib-maven-plugin:build \
-Dimage=gcr.io/$GOOGLE_CLOUD_PROJECT/$APP_NAME:$VERSION
kubectl set image deployment/$APP_NAME \
$APP_NAME=gcr.io/$GOOGLE_CLOUD_PROJECT/$APP_NAME:$VERSION
```
**Run curl** 
```
chmod +x curl.sh
export service1=
./curl.sh 100 $service1
```
**local run in cloud shell**
```
gcloud iam service-accounts create guestbook
export PROJECT_ID=$(gcloud config list --format 'value(core.project)')
gcloud projects add-iam-policy-binding ${PROJECT_ID} \
--member serviceAccount:guestbook@${PROJECT_ID}.iam.gserviceaccount.com \
--role roles/editor
gcloud iam service-accounts keys create \
~/service-account.json \
--iam-account guestbook@${PROJECT_ID}.iam.gserviceaccount.com
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.cloud.gcp.credentials.location=file:///$HOME/service-account.json"
curl http://localhost:8081/service1
```