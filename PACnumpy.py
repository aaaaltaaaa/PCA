import matplotlib.pyplot as plt
from sklearn.datasets import load_iris
import numpy as np
from visualization import visualization

n_components=2
data=load_iris()
y=data.target
X=data.data
mean=np.mean(X,axis=0)
X=X-mean
cov=np.dot(np.transpose(X),X)
e_vals,e_vecs = np.linalg.eig(cov)
sorted_indices = np.argsort(e_vals)
reduce_X=e_vecs[:,sorted_indices[:-n_components-1:-1]]
reduce_X=X.dot(reduce_X)

visualization(y,reduce_X)
