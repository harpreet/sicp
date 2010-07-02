; -*-text-*-
(comment
(list 1 (list 2 (list 3 4)))
;;=>  (1 (2 (3 4)))
)

;; box and arrow notation

         --- ---      --- --- 
        |   |   |--> |   | \ |
         --- ---      --- --- 
          |            |      
          |            |      
          -> 1         |
                      --- ---      --- --- 
                     |   |   |--> |   | \ |
                      --- ---      --- --- 
                       |            |      
                       |            |      
                       -> 2         |
                                   --- ---      --- --- 
                                  |   |   |--> |   | \ |
                                   --- ---      --- --- 
                                    |            |      
                                    |            |      
                                    -> 3         -> 4

The key to draw box-and-arroy diagram is to look at the list and draw
the outermost level elements in the top and then work innerwards (downwards)
from there. So, (1 (2 (3 4))) has two elements at the top :
1 and (list 2 (list 3 4))

(2 (3 4)) has two elements in it: 2 and (list 3 4) and then we have (3 4).
This translated to the above diagram.
