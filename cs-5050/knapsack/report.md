Assignment1 - Knapsack
====

Alan Christensen - A01072246  
January 22, 2014

Recursive Solution
-----

![Recursive Graph](https://raw.github.com/alfy32/school/master/cs-5050/knapsack/test1.plot.png?token=3528383__eyJzY29wZSI6IlJhd0Jsb2I6YWxmeTMyL3NjaG9vbC9tYXN0ZXIvY3MtNTA1MC9rbmFwc2Fjay90ZXN0MS5wbG90LnBuZyIsImV4cGlyZXMiOjEzOTEwNTgxOTd9--cfbe9767647277de929e89e71daeabe86fa90f6a)

  This graph shows the recursive algorithm with the Time on the y axis in log scale and the number of items it tried to put in the bag on the x axis in regular scale. SInce the line exponentialy increases the solution is not very efficient. The slope of the line appears to be one log over 20 items. `10^(1/20) ~ 1.1220`
  
  We were expecting that the problem would result in a runtime of about `2^n`. That means that it ran a little faster than our worst case approximation and ran at about `1.12^n`. So don't count on a worst case as actual run time but it is a good approximation and upper bound.
  
  The first graph was using items sizes from 1-1000 and I tested item sizes 1-100 to see the difference and I ended up with a similar result:
  
![Straigth Recursive](https://raw.github.com/alfy32/school/master/cs-5050/knapsack/test1.100.plot.png?token=3528383__eyJzY29wZSI6IlJhd0Jsb2I6YWxmeTMyL3NjaG9vbC9tYXN0ZXIvY3MtNTA1MC9rbmFwc2Fjay90ZXN0MS4xMDAucGxvdC5wbmciLCJleHBpcmVzIjoxMzkxMDU5NzAxfQ%3D%3D--880c3d25de352d85222c67c547fbf7262e0fcf99)

  This curve ends up more like the worst case. Which it is a worst case. The smaller items always require more calls to fill the bag. With item sizes maxing at 100 it takes 10 max items to fill the 1000 size bag. The curve of this graph is about `10^(1/3) ~ 2`. Meaning we have the 2^n case. 
  
  

Dynamic Programming and Caching Solutions
-----

![Dynamic and Caching](https://raw.github.com/alfy32/school/master/cs-5050/knapsack/test2.plot.png?token=3528383__eyJzY29wZSI6IlJhd0Jsb2I6YWxmeTMyL3NjaG9vbC9tYXN0ZXIvY3MtNTA1MC9rbmFwc2Fjay90ZXN0Mi5wbG90LnBuZyIsImV4cGlyZXMiOjEzOTEwNjAwNjZ9--4c2f308d57848c190a6a08697b6bb82332286d89)

  I plotted the dynamic and caching together to see which is faster and the results show that it is very similar. This also shows the wide range of sizes verses the narrow range of sizes. All of the solutions are so similar on this graph. It seems in the caching the narrow graph better as the number of items increase. I would say this is because we have less items to cache. As we try the narrow items it is more probable that we would try items of the same size or that combinations of items would hit the same part of the cache. The graph seems to show that this could have happened. 
  
  The dynamic solution must fill the entire array every time so the wide vs. narrow range or sizes makes no apparent difference. The runtime depends completely on the size of the cache. 
