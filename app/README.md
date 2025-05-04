# Simple Java Spring Boot Application

This is a **minimal Spring Boot application** designed for learning purposes. It exposes a simple REST endpoint to confirm that the app is running properly before we deploy it to Kubernetes.

## What does it do?

The application provides a single REST endpoint:

```
GET /hello
```

which returns:

```json
{
  "message": "Hello from Kubernetes!"
}
```

## Project Structure

- `src/main/java/com/example/demo/DemoApplication.java`: The main class that starts the Spring Boot app.
- `src/main/java/com/example/demo/controller/HelloController.java`: The REST controller exposing the `/hello` endpoint.
- `pom.xml`: Maven build file with Spring Boot dependencies.

## How to Run Locally

1. Make sure you have **Java 17+** and **Maven** installed.
2. Clone the repository (if not already done):

```bash
git clone <repository-url>
cd <repository-folder>/app
```

3. Run the application:

```bash
mvn spring-boot:run
```

4. Test the endpoint:

```bash
curl http://localhost:8080/hello
```

You should see:

```json
{
  "message": "Hello from Kubernetes!"
}
```

## What's next?

Once you verify that the app is working locally, move on to the [Run Locally with Minikube](../local-deployment/README.md) section to deploy it in a Kubernetes cluster.
