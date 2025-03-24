# CoreShield-Processing-Map-Data-with-Metadata
# 🛠️ CoreShield Assessment - In-Memory Version

This is a **Spring Boot REST API** that processes and merges location data **without using a database**.  
The API reads `locations.json` and `metadata.json`, merges them based on `id`, and provides useful insights.

## 📌 Features
✅ Reads JSON files (`locations.json`, `metadata.json`)  
✅ Merges data where `id` matches  
✅ Stores data **in-memory (No Database Required)**  
✅ Provides **5 REST API endpoints**  
✅ Uses **Spring Boot & Jackson for JSON processing**  

---

## 🚀 Setup & Installation

### **🛠️ Prerequisites**
- Java 17+  
- Apache Maven 3+  

### **📌 Steps to Run**
1. **Clone the repository:**
   ```sh
   git clone https://github.com/your-repo-url.git
   cd coreshield-assessment
