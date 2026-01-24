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


print("\n--- Menu Optimization Insights Started ---")

# STEP 1: Load food demand data
data = pd.read_csv("food_demand.csv")

# STEP 2: Recreate demand labels (same as Feature 2)
def demand_label(order_count):
    if order_count >= 30:
        return "HIGH"
    elif order_count >= 15:
        return "MEDIUM"
    else:
        return "LOW"

data["demand"] = data["orders"].apply(demand_label)

# STEP 3: Generate optimization insights
insights = []

for category in data["category"].unique():
    category_data = data[data["category"] == category]
    avg_orders = category_data["orders"].mean()
    demand = demand_label(avg_orders)

    if demand == "HIGH":
        insights.append(f"Promote {category} items during peak hours")
    elif demand == "MEDIUM":
        insights.append(f"Maintain current strategy for {category} items")
    else:
        insights.append(f"Review or replace low-performing {category} items")

# STEP 4: Save insights to JSON
menu_insights = {
    "menu_optimization_insights": insights
}

with open("ai_output/menu_optimization.json", "w") as f:
    json.dump(menu_insights, f, indent=4)

print("Menu optimization insights generated")

print("\n--- Dish Explanation AI Started ---")

# STEP 1: Load dish info from CSV
dish_df = pd.read_csv("dish_info.csv")

# STEP 2: Convert to dictionary
dish_dict = {}
for _, row in dish_df.iterrows():
    dish_dict[row["dish_name"]] = {
        "ingredients": row["ingredients"],
        "spice_level": row["spice_level"],
        "taste": row["taste"],
        "speciality": row["speciality"]
    }

# STEP 3: Simulate dish name coming from UI / DB
dish_name = "Gulab Jamun"   # change this to test

# STEP 4: Generate explanation
if dish_name in dish_dict:
    info = dish_dict[dish_name]
    explanation = (
        f"{dish_name} is prepared using {info['ingredients']}. "
        f"It has a {info['spice_level']} spice level and a "
        f"{info['taste']} taste. "
        f"Speciality: {info['speciality']}."
    )
else:
    explanation = (
        f"{dish_name} is a popular dish. "
        "Detailed information is currently not available."
    )

# STEP 5: Save output
dish_output = {
    "dish_name": dish_name,
    "explanation": explanation
}

with open("ai_output/dish_explanation.json", "w") as f:
    json.dump(dish_output, f, indent=4)

print("Dish explanation generated")
print(explanation)
