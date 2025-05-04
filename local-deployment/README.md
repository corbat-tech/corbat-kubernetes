# Run Locally with Minikube

In this section, you'll learn how to deploy the simple Java application to a local Kubernetes cluster using **Minikube**. This allows you to experiment with Kubernetes concepts without needing a cloud account.

## Why Minikube?

Minikube runs a single-node Kubernetes cluster on your local machine, perfect for testing and learning.

## Install Minikube

Follow the official installation guide based on your OS:

👉 https://minikube.sigs.k8s.io/docs/start/

Make sure `kubectl` is also installed and configured.

## Start Minikube
```bash
minikube start
```

## Build the Docker Image

To ensure Minikube can use your local Docker image, run:

WSL
```bash
eval $(minikube docker-env)
```
POWER SHELL
```powershell
& minikube docker-env | Invoke-Expression
```


## Check if minikube is in Docker Environment
```bash
minikube status
```
It should return something like this:</br>
*docker-env: in-use*

Then build the image:

```bash
docker build -t corbat-kubernetes-app:latest ../app
```

## Kubernetes Manifests

### 1️⃣ `deployment.yaml`

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: corbat-kubernetes-app
spec:
  replicas: 1
  selector:
    matchLabels:
      app: corbat-kubernetes-app
  template:
    metadata:
      labels:
        app: corbat-kubernetes-app
    spec:
      containers:
        - name: corbat-kubernetes-app
          image: corbat-kubernetes-app:latest
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 8080
```

### 2️⃣ `service.yaml`

```yaml
apiVersion: v1
kind: Service
metadata:
  name: corbat-kubernetes-app-service
spec:
  type: LoadBalancer
  selector:
    app: corbat-kubernetes-app
  ports:
    - port: 8080
      targetPort: 8080

```

## Apply the Manifests

```bash
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
```

## Check the Deployment
```bash
kubectl get pods
kubectl get deployments
kubectl get services
```

## Watch pod logs
```bash
kubectl logs -f $(kubectl get pods -l app=corbat-kubernetes-app -o jsonpath='{.items[0].metadata.name}')
```


## Test the Application from Minikube

Get the Minikube IP:
```bash
minikube ip
```

Then test:
```bash
curl http://192.168.49.2:30007/hello
```

Or get the service URL:
```bash
minikube service corbat-kubernetes-app-service --url
```
And test with Service URL something like:
```bash
curl http://127.0.0.1:52588/hello
```

You should see:
```json
{
  "message": "Hello from Spring Boot with Kubernetes application!"
}
```

## Minikube Tunel
Use `minikube tunnel` to expose the service to your local network. This command creates a network tunnel to your Minikube cluster, allowing you to access services using their NodePort. You need your Service to be LoadBalancer.

```bash
minikube tunnel
```
Check the external IP service:
```bash
kubectl get services
```
You should see something like this:<br>

| NAME                           | TYPE          | CLUSTER-IP     | EXTERNAL-IP | PORT(S)           | AGE    |
|--------------------------------|---------------|----------------|-------------|-------------------|---------|
| corbat-kubernetes-app-service  | LoadBalancer  | 10.97.85.250   | 127.0.0.1   | 8080:31973/TCP    | 5m40s  |

Then test:
```bash
curl http://localhost:8080/hello
```

## What's next?

Once you're comfortable with the local deployment, continue to the [Deploy to AWS NKS](../aws-deployment/README.md) section to push it to the cloud.
