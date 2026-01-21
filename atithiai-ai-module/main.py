import pandas as pd
import json
from sklearn.cluster import KMeans

# STEP 1: Load order data
data = pd.read_csv("orders.csv")

# STEP 2: Convert order_time to datetime
data["order_time"] = pd.to_datetime(data["order_time"])

# STEP 3: Extract hour
data["hour"] = data["order_time"].dt.hour

# STEP 4: Prepare data for ML
X = data[["hour"]]

# STEP 5: Apply K-Means clustering
kmeans = KMeans(n_clusters=3, random_state=42)
data["cluster"] = kmeans.fit_predict(X)

# STEP 6: Find peak cluster
peak_cluster = data["cluster"].value_counts().idxmax()

# STEP 7: Extract peak hours
peak_hours = sorted(data[data["cluster"] == peak_cluster]["hour"].unique())

# 🔧 FIX: convert numpy int to normal Python int
peak_hours = [int(h) for h in peak_hours]

# STEP 8: Prepare JSON output
result = {
    "peak_hours": peak_hours,
    "description": "Predicted peak business hours based on historical order data"
}

# STEP 9: Save JSON file
with open("ai_output/peak_hours.json", "w") as f:
    json.dump(result, f, indent=4)

print("AI Output generated successfully")
