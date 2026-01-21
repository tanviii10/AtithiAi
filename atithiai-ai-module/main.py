# STEP 1: Import libraries
import pandas as pd
import json
from sklearn.cluster import KMeans

# STEP 2: Load the CSV file
data = pd.read_csv("orders.csv")

# STEP 3: Convert order_time to datetime
data["order_time"] = pd.to_datetime(data["order_time"])

# STEP 4: Extract hour from time
data["hour"] = data["order_time"].dt.hour

# STEP 5: Prepare data for ML
X = data[["hour"]]

# STEP 6: Apply K-Means clustering
kmeans = KMeans(n_clusters=3, random_state=42)
data["cluster"] = kmeans.fit_predict(X)

# STEP 7: Find cluster with most orders
peak_cluster = data["cluster"].value_counts().idxmax()

# STEP 8: Get peak hours
peak_hours = sorted(data[data["cluster"] == peak_cluster]["hour"].unique())

# STEP 9: Prepare output
result = {
    "peak_hours": peak_hours,
    "description": "Predicted peak business hours based on historical order data"
}

# STEP 10: Save output as JSON
with open("ai_output/peak_hours.json", "w") as f:
    json.dump(result, f, indent=4)

