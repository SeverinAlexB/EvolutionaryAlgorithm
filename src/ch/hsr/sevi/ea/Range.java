package ch.hsr.sevi.ea;

public class Range {

	  private final int start;
	  private final int end;


	  public Range(int left,int right) {
	    this.start = left;
	    this.end = right;
	  }

	  public int getStart() { return start; }
	  public int getEnd() { return end; }

	}