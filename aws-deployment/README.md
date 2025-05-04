# Deploy to AWS NKS (Amazon EKS)

In this section, you'll learn how to deploy the simple Java application to **Amazon Elastic Kubernetes Service (EKS)**, also referred to here as NKS.

## Prerequisites

- An active AWS account.
- `aws-cli` installed and configured.
- `eksctl` installed.
- `kubectl` installed.
- Docker.

## 1️⃣ Create the EKS Cluster

We will use `eksctl` to create a simple cluster:

```bash
eksctl create cluster \
  --name demo-cluster \
  --region eu-west-1 \
  --nodes 2
```

This may take several minutes.

## 2️⃣ Create an ECR Repository

We need a place to store our Docker image.

```bash
aws ecr create-repository --repository-name demo-app
```

Note the repository URI from the output.

## 3️⃣ Build and Push the Docker Image

Authenticate Docker to ECR:

```bash
aws ecr get-login-password | docker login --username AWS --password-stdin <your-aws-account-id>.dkr.ecr.<region>.amazonaws.com
```

Build the image:

```bash
docker build -t demo-app:latest .
```

Tag the image:

```bash
docker tag demo-app:latest <your-aws-account-id>.dkr.ecr.<region>.amazonaws.com/demo-app:latest
```

Push the image:

```bash
docker push <your-aws-account-id>.dkr.ecr.<region>.amazonaws.com/demo-app:latest
```

## 4️⃣ Update Kubernetes Manifests

Update `deployment.yaml` to use the ECR image:

```yaml
containers:
  - name: demo-app
    image: <your-aws-account-id>.dkr.ecr.<region>.amazonaws.com/demo-app:latest
    ports:
      - containerPort: 8080
```

## 5️⃣ Apply the Manifests

```bash
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
```

## 6️⃣ Access the Application

Get the ELB address:

```bash
kubectl get service demo-app-service
```

If using a `LoadBalancer` type Service, access the app via the external IP:

```bash
curl http://<external-ip>/hello
```

You should see:

```json
{
  "message": "Hello from Kubernetes!"
}
```

---

✅ That's it! Your app is now running in a production-grade Kubernetes cluster on AWS.
