# Kubernetes Learning Repository

Welcome to this repository! Here, you will learn the basics of Kubernetes by deploying a simple Java application using Spring Boot. The primary goal is to **understand Kubernetes concepts step by step**, from local deployment to running in a production-ready cluster on AWS (NKS).

## What is Kubernetes?

Kubernetes (also known as K8s) is an open-source system for automating deployment, scaling, and management of containerized applications. It allows you to:

- Deploy applications quickly and reliably.
- Scale services up and down seamlessly.
- Automate rollouts and rollbacks.
- Manage services declaratively.

In this repository, we focus on **hands-on learning** through a real but minimal application.

## Repository Structure

1. **[Simple Java App](./app/README.md):** A minimal Spring Boot application to demonstrate Kubernetes deployment.
2. **[Run Locally with Minikube](./local-deployment/README.md):** Set up Kubernetes locally and deploy the app.
3. **[Deploy to AWS NKS](./aws-deployment/README.md):** Push the application to the cloud using Amazon's Kubernetes Service.

## Prerequisites

- Java 17+
- Docker
- Kubernetes CLI (`kubectl`)
- Minikube or Kind (for local testing)
- An AWS account (for NKS)

## Why a Simple App?

To focus purely on learning Kubernetes, we use a **very simple Java application**. This removes unnecessary complexity and ensures you understand Kubernetes fundamentals without being distracted by application logic.

---

Get started by moving to the [Simple Java App](./app/README.md) section.
