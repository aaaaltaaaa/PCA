import matplotlib.pyplot as plt
from sklearn.decomposition import PCA
from sklearn.datasets import load_iris
import numpy as np
from visualization import visualization

data=load_iris()
y=data.target
X=data.data

pca=PCA(n_components=3)
reduce_X=pca.fit_transform(X)

visualization(y,reduce_X)
