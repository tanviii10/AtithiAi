import pandas as pd
import json
from sklearn.tree import DecisionTreeClassifier

print("\n--- Food Demand Prediction Started ---")

# STEP 1: Load food demand data
data = pd.read_csv("food_demand.csv")

# STEP 2: Convert category names to numbers
data["category_code"] = data["category"].astype("category").cat.codes

# STEP 3: Create demand labels
def demand_label(order_count):
    if order_count >= 30:
        return "HIGH"
    elif order_count >= 15:
        return "MEDIUM"
    else:
        return "LOW"

data["demand"] = data["orders"].apply(demand_label)

# STEP 4: Prepare input (X) and output (y)
X = data[["category_code", "hour"]]
y = data["demand"]

# STEP 5: Train Decision Tree model
model = DecisionTreeClassifier(random_state=42)
model.fit(X, y)

# STEP 6: Predict demand for each category (example hour = 13)
result = {}

for category in data["category"].unique():
    code = data[data["category"] == category]["category_code"].iloc[0]

    input_df = pd.DataFrame(
        [[code, 13]],
        columns=["category_code", "hour"]
    )

    prediction = model.predict(input_df)[0]
    result[category] = prediction

# STEP 7: Save output as JSON
with open("ai_output/food_demand.json", "w") as f:
    json.dump(result, f, indent=4)

print("Food demand prediction completed")
print(result)
