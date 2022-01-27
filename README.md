
**One time setup**

git clone https://github.com/sdhimmar006/service1.git
chmod +x service1/mvnw
export SERVICE1_PORT=8081
export APP_NAME=service1
export GOOGLE_CLOUD_PROJECT=`gcloud config list --format="value(core.project)"`
export VERSION=$(($(date +%s%N)/1000000))
export baseurl=
./mvnw -DskipTests com.google.cloud.tools:jib-maven-plugin:build \
-Dimage=gcr.io/$GOOGLE_CLOUD_PROJECT/$APP_NAME:$VERSION
kubectl create deployment $APP_NAME \
--image=gcr.io/$GOOGLE_CLOUD_PROJECT/$APP_NAME:$VERSION
kubectl create service loadbalancer1 $APP_NAME --tcp=$SERVICE1_PORT:$SERVICE1_PORT

**Update an existing service**

export VERSION=$(($(date +%s%N)/1000000))
./mvnw -DskipTests com.google.cloud.tools:jib-maven-plugin:build \
-Dimage=gcr.io/$GOOGLE_CLOUD_PROJECT/$APP_NAME:$VERSION
kubectl set image deployment/$APP_NAME \
$APP_NAME=gcr.io/$GOOGLE_CLOUD_PROJECT/$APP_NAME:$VERSION

**Run curl** 

chmod +x curl.sh
export service1=
./curl.sh 100 ${service1}