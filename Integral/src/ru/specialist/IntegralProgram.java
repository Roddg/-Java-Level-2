package ru.specialist;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.DoubleFunction;

public class IntegralProgram {
	
	public static final int STEPS = 10000000;
	public static final int TASKS = 10;
	
	public static double singleThread(DoubleFunction<Double> f, double a, double b, int steps) {
		double h = (b - a) / steps;
		double summa = 0d;
		
		for(int i = 0; i < steps; i++) {
			double x = a + h*i + h / 2;
			double y = f.apply(x);
			summa += y*h;
		}
		return summa;
	}
	
	public static double multiThread(DoubleFunction<Double> f, double a, double b) throws InterruptedException, ExecutionException {
		ExecutorService p = Executors.newFixedThreadPool(TASKS);
		double h = (b-a) / TASKS;
		
		Future<Double>[] tasks = new Future[TASKS];
		for(int i=0; i < TASKS; i++) {
			double ax = a + h*i;
			double bx = ax + h;
			tasks[i] = p.submit(
					/*new Callable<Double>() {
						public Double call() throws Exception {
							return singleThread(f, ax, bx, STEPS/TASKS);
						}
					}*/
					
					()-> singleThread(f, ax, bx, STEPS/TASKS)
					
					);
		}
		
		double summa =0d;
		for(Future<Double> task : tasks) 
			summa += task.get();
		
		p.shutdown();
		
		return summa;
	}
	public static double multiThread2(DoubleFunction<Double> f, double a, double b) throws InterruptedException, ExecutionException {
		class Pair {
			double ax;
			double bx;
		}
		
		double h = (b-a) / TASKS;
		Pair[] pairs = new Pair[TASKS];
		for(int i=0; i < TASKS; i++) {
			pairs[i] = new Pair();
			pairs[i].ax = a + h*i;
			pairs[i].bx = a + h*(i+1);
		}
		
		return Arrays.asList(pairs).parallelStream().
			mapToDouble( p -> singleThread(f, p.ax, p.bx, STEPS/TASKS)).sum();
		
		
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		singleThread(Math::sin, 0, Math.PI/2, STEPS);
		singleThread(Math::sin, 0, Math.PI/2, STEPS);
		long t1 = System.currentTimeMillis();
		double r1 = singleThread(Math::sin, 0, Math.PI/2, STEPS);
		long t2 = System.currentTimeMillis();
		System.out.printf("Single Thread: %f Time: %d\n", r1, t2-t1);
		
		multiThread(Math::sin, 0, Math.PI/2);
		multiThread(Math::sin, 0, Math.PI/2);
		long t3 = System.currentTimeMillis();
		double r2 = multiThread(Math::sin, 0, Math.PI/2);
		long t4 = System.currentTimeMillis();
		System.out.printf("Multi  Thread: %f Time: %d\n", r2, t4-t3);

		multiThread2(Math::sin, 0, Math.PI/2);
		multiThread2(Math::sin, 0, Math.PI/2);
		long t5 = System.currentTimeMillis();
		double r3 = multiThread2(Math::sin, 0, Math.PI/2);
		long t6 = System.currentTimeMillis();
		System.out.printf("Multi Thread2: %f Time: %d\n", r3, t6-t5);
	}

}
