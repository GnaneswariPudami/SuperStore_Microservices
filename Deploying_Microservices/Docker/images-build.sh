#!/bin/bash

cd /Users/j84527082/SuperStore_Microservices/notifications/
docker build -t notifications-service:1.0 .

cd /Users/j84527082/SuperStore_Microservices/product/
docker build -t product-service:1.0 .

cd /Users/j84527082/SuperStore_Microservices/useraccount/
docker build -t useraccount-service:1.0 .

cd /Users/j84527082/SuperStore_Microservices/order/
docker build -t order-service:1.0 .

echo "docker images created"
docker image ls


#docker run -d -e AWS_REGION=eu-west-2 -e AWS_ACCESS_KEY_ID=AKIA4MI2KB54JQAEGY4G -e AWS_SECRET_ACCESS_KEY=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 -p 8080:8080 notifications-service:1.0
#docker run -d -e AWS_REGION=eu-west-2 -e AWS_ACCESS_KEY_ID=AKIA4MI2KB54JQAEGY4G -e AWS_SECRET_ACCESS_KEY=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 -p 8081:8081 product-service:1.0
#docker run -d -e AWS_REGION=eu-west-2 -e AWS_ACCESS_KEY_ID=AKIA4MI2KB54JQAEGY4G -e AWS_SECRET_ACCESS_KEY=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 -p 8082:8082 useraccount-service:1.0
#docker run -d -e AWS_REGION=eu-west-2 -e AWS_ACCESS_KEY_ID=AKIA4MI2KB54JQAEGY4G -e AWS_SECRET_ACCESS_KEY=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 -p 8083:8083 order-service:1.0

docker ps



