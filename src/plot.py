# import matplotlib as mpl
# from mpl_toolkits.mplot3d.axes3d import Axes3D
# import matplotlib.pyplot as plt
# from matplotlib.ticker import LinearLocator, FormatStrFormatter
# from matplotlib import cm
# import numpy as np
# from py4j.java_gateway import JavaGateway
# gateway = JavaGateway()
#
#
#
# def plot(x, y, z):
#     fig = plt.figure()
#     ax = fig.gca(projection='3d')
#     x, y = np.meshgrid(x, y)
#     # Plot the surface.
#     surf = ax.plot_surface(X, Y, Z, cmap=cm.coolwarm,
#                            linewidth=0, antialiased=False)
#
#     # Customize the z axis.
#     ax.set_zlim(-1.01, 1.01)
#     ax.zaxis.set_major_locator(LinearLocator(10))
#     ax.zaxis.set_major_formatter(FormatStrFormatter('%.02f'))
#
#     # Add a color bar which maps values to colors.
#     fig.colorbar(surf, shrink=0.5, aspect=5)
#     plt.show()
#
#
#
# # Make data.
# U = np.array(list(gateway.entry_point.getX()))
# n = gateway.entry_point.getN()
# m = gateway.entry_point.getM()
# #X = np.array(list(gateway.entry_point.getN()))
# #Y = np.array(list(gateway.entry_point.getM()))
# """print(n)
# print(m)
# print(U)"""
#
# def retrouver(n,m,U):
#     Z=np.zeros((n-1,m-1))
#     for num in range(U.size):
#         j=(num//(n-1))
#         i=(num%(n-1))
#         Z[i][j]=U[num]
#     return Z
#
#
#
# X=np.linspace(0,1,n-1)
# Y=np.linspace(0,1,m-1)
# print(X)
# print(Y)
#
# X, Y = np.meshgrid(X, Y)
# Z= retrouver(n,m,U)
# plot(X,Y,Z)
# print(Z)


import numpy as np
from pylab import *
from mpl_toolkits.mplot3d import Axes3D
from py4j.java_gateway import JavaGateway
gateway = JavaGateway()
fig = figure()
ax = Axes3D(fig)
a=0
b=1

U = np.array(list(gateway.entry_point.getX()))
n = gateway.entry_point.getN()
m = gateway.entry_point.getM()


X = np.linspace(a+(1/n), b-(1/n), n-1)
Y = np.linspace(a+(1/m), b-(1/m), m-1)
X, Y = np.meshgrid(X, Y)

# ligne=[0 for i in range((n+1)*(m+1))]
# for k in range(len(ligne)):
#     i=(k%(n+1))
#     j=(k//(n+1))
#     ligne[k]=(i*(b-a)/n)**2+((b-a)*j/m)**2

# x=np.zeros(shape=(m-1,n-1))
#
# for i in range(len(U)):
#     row = i%(n-1)
#     col = i//(n-1)
#     x[col][row] = U[i]
# print(x)
#
# # Z=X**2+Y**2
# # ax.plot_surface(X, Y, Z, rstride=1, cstride=1, cmap='binary')
# ax.plot_surface(X, Y, x, rstride=1, cstride=1, cmap='hot')
# show()

x=np.zeros(shape=(m-1,n-1))
for i in range(len(U)):
    row = i%(n-1)
    col = i//(n-1)
    x[col][row] = U[i]

ax.plot_surface(X, Y, x, rstride=1, cstride=1, cmap='hot')
print("exe")
show()