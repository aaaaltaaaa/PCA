from mpl_toolkits import mplot3d
import matplotlib.pyplot as plt
from sklearn.decomposition import PCA
from sklearn.datasets import load_iris
def visualization(label, reduce_X):
    red_x,red_y,red_z=[],[],[]
    blue_x,blue_y,blue_z=[],[],[]
    green_x,green_y,green_z=[],[],[]
    if len(reduce_X[0])==2:
        for i in range(len(reduce_X)):
            if label[i]==0:
                red_x.append(reduce_X[i][0])
                red_y.append(reduce_X[i][1])
            elif label[i]==1:
                blue_x.append(reduce_X[i][0])
                blue_y.append(reduce_X[i][1])
            else:
                green_x.append(reduce_X[i][0])
                green_y.append(reduce_X[i][1])

        plt.scatter(red_x,red_y,c='r',marker='x')
        plt.scatter(blue_x,blue_y,c='b',marker='D')
        plt.scatter(green_x,green_y,c='g',marker='.')

    else:
        ax = plt.axes(projection='3d')
        for i in range(len(reduce_X)):
            if label[i]==0:
                red_x.append(reduce_X[i][0])
                red_y.append(reduce_X[i][1])
                red_z.append(reduce_X[i][2])
            elif label[i]==1:
                blue_x.append(reduce_X[i][0])
                blue_y.append(reduce_X[i][1])
                blue_z.append(reduce_X[i][2])
            else:
                green_x.append(reduce_X[i][0])
                green_y.append(reduce_X[i][1])
                green_z.append(reduce_X[i][2])

        ax.scatter3D(red_x,red_y,red_z,c='r',marker='x')
        ax.scatter3D(blue_x,blue_y,blue_z,c='b',marker='D')
        ax.scatter3D(green_x,green_y,green_z,c='g',marker='.')

    plt.show()