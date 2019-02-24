#include"App.h"
#include<stdlib.h>
#include<dlfcn.h>

JNIEXPORT jint JNICALL Java_App_calcBill(JNIEnv *env,jclass jobj,jintArray arr)
{
	int i,sum=0;
	jsize len=(*env)->GetArrayLength(env,arr);

	void *p=NULL;
	int(*countTotal)(int[],int)=NULL;

	jint *params=(*env)->GetIntArrayElements(env,arr,0);

	p=dlopen("/home/lenovo/Niyander_Project/Computer_Shopping_Portal/calcLogic.so",RTLD_LAZY);

	if(!p)
	{
		printf("Unable to load library : %s\n",dlerror());
	}
	countTotal=dlsym(p,"countTotal");
	if(countTotal==NULL)
	{
		printf("Unable to get address of function :%s\n",dlerror());
	}
	sum=countTotal(params,len);

	(*env)->ReleaseIntArrayElements(env,arr,params,0);
	dlclose(p);
	return sum;
}
