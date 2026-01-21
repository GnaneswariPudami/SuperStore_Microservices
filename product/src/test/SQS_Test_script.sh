#!/bin/bash

# AWS CLI configuration (ensure you have AWS CLI installed and configured)
AWS_REGION="eu-west-2"  # e.g., us-east-1
QUEUE_URL="https://sqs.eu-west-2.amazonaws.com/850995580792/superstore-productservice" # e.g., https://sqs.us-east-1.amazonaws.com/123456789012/MyQueue

# Poll for a single message
message=$(aws sqs receive-message \
    --queue-url "$QUEUE_URL" \
    --region "$AWS_REGION" \
    --max-number-of-messages 1)

# Check if a message was received
if [[ -n "$message" ]]; then
  # Extract the message body and receipt handle
  message_body=$(echo "$message" | jq -r '.Messages[0].Body')
  receipt_handle=$(echo "$message" | jq -r '.Messages[0].ReceiptHandle')

  echo "Message received:"
  echo "Body: $message_body"

  # Delete the message (optional, but recommended for testing)
  aws sqs delete-message \
      --queue-url "$QUEUE_URL" \
      --receipt-handle "$receipt_handle" \
      --region "$AWS_REGION"

  echo "Message deleted."

else
  echo "No messages in the queue."
fi