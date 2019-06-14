import numpy as np
from pylab import *
from mpl_toolkits.mplot3d import Axes3D

fig = figure()
ax = Axes3D(fig)
n=40
m=40
a=0
b=1

X = np.linspace(a, b, n+1)
Y = np.linspace(a, b, m+1)
X, Y = np.meshgrid(X, Y)
# print(X.shape)
# ligne=[0 for i in range((n+1)*(m+1))]
# for k in range(len(ligne)):
#     i=(k%(n+1))
#     j=(k//(n+1))
#     ligne[k]=(i*(b-a)/n)**3+((b-a)*j/m)**3
#
# x=np.zeros(shape=(m+1,n+1))
# for i in range(len(ligne)):
#     row = i%(n+1)
#     col = i//(n+1)
#     x[col][row] = ligne[i]
# print(x)

Z=np.sin(X)
ax.plot_surface(X, Y, Z, rstride=1, cstride=1, cmap='hot')
# ax.plot_surface(X, Y, x, rstride=1, cstride=1, cmap='hot')
show()