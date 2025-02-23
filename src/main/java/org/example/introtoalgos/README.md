# Chapter 2 (Sorting)

## Exercise

### 2.1-4

Write pseudocode for linear search, which scans through the array from beginning
to end, looking for x . Using a loop invariant, prove that your algorithm is
correct. Make sure that your loop invariant fulfills the three necessary properties.

SEARCH(A, n, target)

FOR i = i to n:
if target == A[n]
return i
return -1

### Merge Sort

To understand how the MERGE procedure works, let’s return to our card-playing
motif. Suppose that you have two piles of cards face up on a table. Each pile is
sorted, with the smallest-value cards on top. You wish to merge the two piles
into a single sorted output pile, which is to be face down on the table. The basic
step consists of choosing the smaller of the two cards on top of the face-up piles,
removing it from its pile4which exposes a new top card4and placing this card
face down onto the output pile. Repeat this step until one input pile is empty, at
which time you can just take the remaining input pile and üip over the entire pile,
placing it face down onto the output pile.