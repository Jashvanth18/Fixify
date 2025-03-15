# Fixify - Code Explanation and Debugging Assistant

## Project Overview
Fixify is a Streamlit-based web application that leverages the Groq API with the LLaMA3-8b-8192 model to provide intelligent code explanation and debugging assistance. This tool allows developers to input code and receive detailed explanations and debugging tips.

## Features
- **Code Analysis and Debugging**: Analyzes code and provides explanations for logic errors, syntax issues, and potential improvements.
- **Groq API Integration**: Utilizes the LLaMA3-8b-8192 model for enhanced natural language processing.
- **User-Friendly Interface**: Built with Streamlit for an intuitive and interactive user experience.

## Tech Stack
- Python
- Streamlit
- Groq API
- Requests Library
- Dotenv for environment variable management

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Jashvanth18/Fixify.git
   ```
2. Navigate to the project folder:
   ```bash
   cd Fixify
   ```
3. Create a virtual environment and activate it:
   ```bash
   python -m venv venv
   .\venv\Scripts\activate   # On Windows
   source venv/bin/activate   # On macOS/Linux
   ```
4. Install the dependencies:
   ```bash
   pip install -r requirements.txt
   ```
5. Add your Groq API key to a `.env` file:
   ```plaintext
   GROQ_API_KEY=your_api_key_here
   ```

## Running the Application
```bash
streamlit run app.py
```

## Usage
1. Enter the code snippet in the text area.
2. Click the "Submit" button to get explanations and debugging tips.

## Contributing
Contributions are welcome! Feel free to submit pull requests or report issues.

## License
MIT License

## Contact
For any queries or support, reach out to [Jashvanth](mailto:jashvanth@example.com).

