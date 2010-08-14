(comment
  "This is because, the flatmap calculates entire queens-col, board-size times.
   The solution from Reasoner is a bit un-natural as the natural abstraction is
   to add a new set of positions for column k to the queens-pos for (k - 1) columns.
   So, if the solution in 2.42 took T, then, this solution will be in the order of
   O(T^n), where n is the board-size."
  )