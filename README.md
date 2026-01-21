# SuperStore_Microservices
4 microservices connecting to AWS Services and connecting to each other using REST API

[//]: # (Github Commands)
```bash
gh repo clone GnaneswariPudami/SuperStore_TerraformCode
gh repo clone GnaneswariPudami/SuperStore_Microservices

git add .
git commit -m "your commit message"
git push origin main
```

[//]: # (AWS CLI Commands)
```bash

[//]: # (Terraform Commands)
[//]: # (Backend setup for Terraform)
```bash
terraform init
terraform plan 
terraform apply
```

[//]: # (Docker Commands)
```bash

[//]: # (EKS Cluster creation and other services creation using Terraform code)
```bash
terraform init
terraform plan --var-file=dev.tfvars
terraform apply --auto-approve --var-file=dev.tfvars
```

[//]: # (Deployment of Microservices to EKS Cluster)
```
1. Run github workflow eks-build-and-deploy-config-server.yml 
2. Run github workflow eks-build-and-deploy-notification-services.yml
```

aws configure
aws eks update-kubeconfig --region <region-code> --name <cluster-name>


[//]: # (Kubernetes Commands)
```bash
kubectl get nodes
kubectl get pods
kubectl get services
kubectl get deployments
```

[//]: # (Accessing the Microservices)
```bash
1. Access Config Server Service using the LoadBalancer DNS and port
http://<Config-Server-LoadBalancer-DNS>:<port>/actuator/health
2. Access Notification Service using the LoadBalancer DNS and port
http://<Notification-Service-LoadBalancer-DNS>:<port>/notifications/health
```

    
