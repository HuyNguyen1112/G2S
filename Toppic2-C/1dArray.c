#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

//===KHAI BAO HAM===
void inputArray(int arr[], int *n);
void outputArray(int arr[], int n);
void sortDescending(int arr[], int n);
void printDescending (int arr[],int n);
bool checkAllOdd(int arr[], int n);
void resultAllOdd(int arr[], int n);
int searchValue(int arr[], int n, int x);
bool isPrime(int num);
void displayPrimes(int arr[], int n);

//===CHUONG TRINH CHINH===
int main() {
    int arr[100];
	int n = 0; 
	int choice;
    while (1) {
        printf("\n===== MENU =====\n");
        printf("1. Nhap mang\n");
        printf("2. Xuat mang\n");
        printf("3. In mang giam dan\n");
        printf("4. Kiem tra toan bo so le\n");
        printf("5. Tim kiem gia tri\n");
        printf("6. Hien thi so nguyen to\n");
        printf("7. Thoat\n");
        printf("Nhap lua chon: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1: inputArray(arr, &n); break;
            case 2: outputArray(arr, n); break;
            case 3: printDescending(arr, n); break;
            case 4: resultAllOdd(arr, n); break;
            case 5: 
            {
            	int x; 
    			printf("Nhap gia tri can tim: ");
    			scanf("%d", &x);
    			printf("So %d xuat hien %d lan", x, searchValue(arr, n, x));
				break;
			}
            case 6: displayPrimes(arr, n); break;
            case 7: 
			{
                int confirm;
                printf("Ban co chac muon thoat? (1=Co, khac=Khong): ");
                scanf("%d", &confirm);
                if (confirm == 1) 
					exit(0);
                break;
            }
            default: printf("Lua chon khong hop le!\n");
        }
    }
    return 0;
}

//===DINH NGHIA HAM===
// Ham nhap mang
void inputArray(int arr[], int *n) 
{
    do 
	{
        printf("Nhap so phan tu (1-100): ");
        scanf("%d", n);
    } 
	while (*n < 1 || *n > 100);

    for (int i = 0; i < *n; i++) 
	{
        printf("arr[%d] = ", i+1);
        scanf("%d", &arr[i]);
    }
}

// Ham xuat mang
void outputArray(int arr[], int n) 
{
    printf("Mang: ");
    for (int i = 0; i < n; i++) 
	{
        printf("%d ", arr[i]);
    }
    printf("\n");
}

// Ham in mang giam dan
void sortDescending(int arr[], int n) 
{
	int temp;
    for (int i = 0; i < n - 1; i++) 
	{
        for (int j = i + 1; j < n; j++) 
		{
            if (arr[i] < arr[j]) 
			{
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }
}

void printDescending (int arr[],int n)
{
	int tempArr[100];
	for (int i = 0; i < n; i++) 
		tempArr[i] = arr[i];
	sortDescending(tempArr,n);
	printf("Mang giam dan: ");
    for (int i = 0; i < n; i++) 
	{
        printf("%d ", tempArr[i]);
    }
    printf("\n");
}

// Ham kiem tra toan bo so le
bool checkAllOdd(int arr[], int n) 
{
    for (int i = 0; i < n; i++) 
	{
        if (arr[i] % 2 == 0) 
		{
            return false;
        }
    }
	return true;  
}

void resultAllOdd(int arr[], int n)
{
	if (checkAllOdd(arr,n)) 
		printf("Tat ca phan tu la so le.\n");
    else 
		printf("Mang co phan tu khong phai so le.\n");
}


// Ham tim gia tri
int searchValue(int arr[], int n, int x) 
{
    int count = 0;
    for (int i = 0; i < n; i++) 
	{
        if (arr[i] == x) count++;
    }
    return count;
}

// Ham kiem tra so nguyen to
bool isPrime(int num) 
{
    if (num < 2)
		return false;
    for (int i = 2; i * i <= num; i++) 
	{
        if (num % i == 0) 
			return false;
    }
    return true;
}

// Ham in ra day so nguyen to
void displayPrimes(int arr[], int n) 
{
    printf("Cac so nguyen to trong mang: ");
    for (int i = 0; i < n; i++) 
	{
        if (isPrime(arr[i])) 
			printf("%d ", arr[i]);
    }
    printf("\n");
}
