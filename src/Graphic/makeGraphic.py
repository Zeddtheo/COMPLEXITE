import pandas as pd
import matplotlib.pyplot as plt

Df = pd.read_csv("for_graphic.csv")
#Df.transpose()
plt.title("titre")
plt.xlabel("xlabel")
plt.ylabel("ylabel")
print(Df)
for index, row in Df.iterrows():
    plt.plot(row, label=["1","2","3","4","5","6","7","8","9"][index] )
plt.legend()
plt.show()