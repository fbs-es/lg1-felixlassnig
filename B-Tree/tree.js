class Node {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

class binarySearchTree {
  constructor() {
    this.root = null;
  }

  insert(value) {
    const newNode = new Node(value);

    if (this.root === null) {
      this.root = newNode;
      return;
    }

    let current = this.root;

    while (true) {
      if (value === current.value) return;

      if (value < current.value) {
        if (current.left === null) {
          current.left = newNode;
          return;
        }
        current = current.left;
      } else {
        if (current.right === null) {
          current.right = newNode;
          return;
        }
        current = current.right;
      }
    }
  }

  // Inorder: Links -> Root -> Rechts
  inOrder(node = this.root, list = []) {
    if (!node) return list;

    this.inOrder(node.left, list);
    list.push(node.value);
    this.inOrder(node.right, list);

    return list;
  }

  postOrder(node = this.root, list = []) {
    if(!node) return list;

    this.postOrder(node.left, list)
    this.postOrder(node.right, list)
    list.push(node.value)

    return list;
  }

  preOrder(node = this.root, list = []) {
    if (!node) return list;

    list.push(node.value)
    this.preOrder(node.left, list)
    this.preOrder(node.right, list)

    return list;
  }

  // Gibt den Baum strukturiert im Terminal aus und markiert die Wurzel
  printTree(node = this.root, prefix = "", isLeft = true) {
    if (!node) return;

    if (node.right) {
      this.printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
    }

    console.log(prefix + (isLeft ? "└── " : "┌── ") + node.value + (node === this.root ? " (ROOT)" : ""));

    if (node.left) {
      this.printTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
    }
  }

}

// Testlauf
const tree = new binarySearchTree();
const zahlen = [8, 3, 10, 1, 6, 14, 4, 7, 13];

for (let i = 0; i < zahlen.length; i++) {
  tree.insert(zahlen[i]);
}

console.log("Inorder:", tree.inOrder());
console.log("postOrder:", tree.postOrder());
console.log("preOrder:", tree.preOrder());

tree.printTree();
