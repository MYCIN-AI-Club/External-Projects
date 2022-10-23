#include <stdio.h>

#include <stdlib.h>

#include <conio.h>

void shellsort(int arr[], int num)

{

    int i, j, k, temp;

    for (i = num / 2; i > 0; i = i / 2)

    {

        for (j = i; j < num; j++)

        {

            for(k = j - i; k >= 0; k = k - i)

            {

                if (arr[k+i] >= arr[k])
                    break;
                else

                {

                    temp = arr[k];

                    arr[k] = arr[k+i];

                    arr[k+i] = temp;

                }
            }
        }
    }
}

int main()
{
    int arr[100];

    int k, vals;

    printf("Enter total no. of elements : ");

    scanf("%d", &vals);

    printf("\nEnter the %d values: ", vals);

    for (k = 0 ; k < vals; k++)

    {

        scanf("%d", &arr[k]);

    }

    shellsort(arr, vals);

    printf("\n Array after sorting: ");

    for (k = 0; k < vals; k++)

        printf("%d ", arr[k]);

    return 0;

}
