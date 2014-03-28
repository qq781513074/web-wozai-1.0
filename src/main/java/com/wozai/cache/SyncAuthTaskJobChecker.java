package com.wozai.cache;

public class SyncAuthTaskJobChecker {
    Runnable r ;
    public SyncAuthTaskJobChecker(Runnable r){
        this.r = r;
    }
	public void excute(){
		int i = 3;
		Thread thread = new Thread(r);
		while(i-- >0){
			try{
				thread.start();
				return;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
