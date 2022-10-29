void BTtoDLL(Node *root, Node **head_reference, Node **previous)
{
   if (!root) 
      return;

   BTtoDLL(root->left, head_reference, previous);

   if (*head_reference == NULL)
       *head_reference = root;
   else
   {
       root->left = *previous;
       *previous->right = root;
   }
  
   *previous = root;

   BTtoDLL(root->right, head_reference, previous);
}
