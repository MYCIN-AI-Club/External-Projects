#include<stdio.h>
int main()
{
  int r,c,count=0,k=0;
    printf("enter the no. of Rows : ");
    scanf("%d",&r);
    printf("enter the no. of Rows : ");
    scanf("%d",&c); 
    int a[r][c];
    printf("Enter the Sparse Matrix : \n");
    for(int i=0 ; i<r ; i++)
    {
        for(int j=0 ; j<c ; j++)
        {
            scanf("%d",&a[i][j]);
        }
    }
    printf("sparse matrix : \n");
    for(int i=0 ; i<r ; i++)
    {
        for(int j=0 ; j<c ; j++)
        {
            printf("%d\t",a[i][j]);
        }
        printf("\n");
    }
    
    for(int i=0 ; i<r ; i++)
    {
        for(int j=0 ; j<c ; j++)
        {
            if(a[i][j]!=0)
            {
                count++;
            }
        }
    }
    int b[3][count];
    printf("Required Output : \n");
    for(int i=0 ; i<r ; i++)
    {
        for(int j=0 ; j<c; j++)
        {
            if(a[i][j]!=0)
            {
            b[0][k]=i;
            b[1][k]=j;
            b[2][k]=a[i][j];
            k++;
            }
        }
    }
    for(int i=0 ; i<3 ; i++)
    {
        for(int j=0 ; j<count; j++)
        {
            printf("%d\t",b[i][j]);
        }
        printf("\n");
    }
    return 0;
}
