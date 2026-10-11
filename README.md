# DAA_Bonus_task
The goal of this project is to compare the performance of BST and Hash Table.
I implemented both data structures in Java and tested three operations: insertion, searching, and deletion.

Binary Search Tree (BST)
- Each node has a key, left child, and right child.Smaller values go left, larger values go right.I implemented insert, search, and delete.

Hash Table
- Uses separate chaining with LinkedList to handle collisions.Uses a hash function to find the bucket.Automatically resizes when the load factor is greater than 0.75.
## Time Complexity
| Structure | Operation | Best | Expected | Worst |
|---|---|---|---|---|
| BST | Insert | Θ(1) | O(log n) | Θ(n) |
| BST | Search | Θ(1) | O(log n) | Θ(n) |
| BST | Delete | Θ(1) | O(log n) | Θ(n) |
| Hash table | Insert | Θ(1) | O(1), amortized | Θ(n) |
| Hash table | Search | Θ(1) | O(1) | Θ(n) |
| Hash table | Delete | Θ(1) | O(1) | Θ(n) |
## Test
I tested both structures with 100, 1,000, and 10,000 elements.
- Used random order of unique numbers.
- Measured time using System.nanoTime().
- Used 5 warm-up runs and 20 test runs.
- Calculated average execution times in ms.

## Conclusion
In my tests Hash Table was faster at searching, especially with 10,000 elements.
BST was faster at insertion and deletion in these tests. Hash Table needs extra work for resizing, which can affect insertion time.
The performance also depends on the input data and implementation.


