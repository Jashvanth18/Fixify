import requests
import streamlit as st
from dotenv import load_dotenv
import os

# Load environment variables from .env file
load_dotenv('API_KEY.env')
groq_api_key = os.getenv("GROQ_API_KEY")

# Check if API key is properly set
if not groq_api_key:
    st.error("API key not found! Make sure it's set in the environment variables.")
else:
    # Define API endpoint
    api_endpoint = "https://api.groq.com/openai/v1/chat/completions"  # Groq API endpoint

    # Function to send code for explanation and debugging
    def get_code_explanation_and_debugging(code_input):
        headers = {
            'Authorization': f'Bearer {groq_api_key}',
            'Content-Type': 'application/json'
        }
        
        data = {
            "model": "llama3-8b-8192",  # Use the desired model
            "messages": [
                {"role": "system", "content": "You are an AI assistant that explains and debugs code."},
                {"role": "user", "content": f"Explain and debug the following code: {code_input}"}
            ],
            "max_tokens": 150,
            "temperature": 0.5
        }
        
        try:
            response = requests.post(api_endpoint, headers=headers, json=data)
            response.raise_for_status()  # Raise an error for bad responses (4xx or 5xx)
            return response.json()['choices'][0]['message']['content']
        except requests.exceptions.HTTPError as err:
            return f"HTTP error occurred: {err.response.status_code} - {err.response.text}"  # Provide error message for HTTP errors
        except Exception as e:
            return f"An error occurred: {e}"  # General error handling

    # Streamlit UI
    st.title("Code Explanation and Debugging Assistant")

    # Text input for code to debug
    code_input = st.text_area("Enter your code here:")

    # Button to submit code
    if st.button("Submit"):
        if code_input:
            explanation = get_code_explanation_and_debugging(code_input)
            st.write("### Explanation and Debugging Tips:")
            st.write(explanation)
        else:
            st.write("Please enter some code.")