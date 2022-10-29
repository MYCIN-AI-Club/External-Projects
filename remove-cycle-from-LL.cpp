Node* getLastNode(Node* head) 
{
   Node* slow = head;
   Node* fast = head;
  
   while (fast->next != NULL) 
   { 
       slow = slow->next; 
       fast = fast->next->next; 
       if (slow == fast) 
           break; 
   }

   if (fast->next == NULL) 
   {
       return NULL;
   }
   slow = head; 
   
   while (slow->next != fast->next) 
   { 
       slow = slow->next; 
       fast = fast->next; 
   }
   
   return fast;
}
Node* getLastNode = findStartOfLoop(head);
getLastNode->next = null;
