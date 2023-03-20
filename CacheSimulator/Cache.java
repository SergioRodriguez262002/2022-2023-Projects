
/**
 * Class implementing a basic cache with a configurable size and associativity.
 * 
 * The cache is backed-up by a "Memory" object which actually stores stores the
 * values -- on a cache miss, the Memory should be accessed.
 * 
 */
public class Cache implements ReadOnlyCache {
	private Memory m_memory;
	private byte[][] data;
	private int[] tagArray;
	private boolean[] valid;
	private int m_blockCount, m_bytesPerBlock, m_associativity;

	/**
	 * Constructor
	 * 
	 * @param memory        - An object implementing the Memory functionality. This
	 *                      should be accessed on a cache miss
	 * @param blockCount    - The number of blocks in the cache.
	 * @param bytesPerBlock - The number of bytes per block in the cache.
	 * @param associativity - The associativity of the cache. 1 means direct mapped,
	 *                      and a value of "blockCount" means fully-associative.
	 */
	public Cache(Memory memory, int blockCount, int bytesPerBlock, int associativity) {
		m_memory = memory;
		data = new byte[blockCount][bytesPerBlock];
		valid = new boolean[blockCount];
		tagArray = new int[blockCount];

		m_blockCount = blockCount;
		m_bytesPerBlock = bytesPerBlock;
		m_associativity = associativity;

	}

	/**
	 * Method to retrieve the value of the specified memory location.
	 * 
	 * @param address - The address of the byte to be retrieved.
	 * @return The value at the specified address.
	 */
	public byte load(int address) {
		// TODO: implement the logic to perform the specified load
		// using caching logic. This implementation does not do any caching,
		// it just immediately accesses memory, and is not correct.

		// calculate offset index then the tag
		int offset = address % m_bytesPerBlock;
		int index = (address / m_bytesPerBlock) % m_blockCount;
		int tag = address - offset - index;

		// cache structure in the byte array data[index][offset]

		if (tag == tagArray[index] && valid[index]) { // Hit
			/* On a hit return the requested data */
			return data[index][offset];
		} else { // Miss
			/*
			 * On a miss set the tag to the tag, valid bit to true at block index, and pull
			 * entire block from memory. Finally return the data.
			 */
			tagArray[index] = tag;
			valid[index] = true;
			data[index] = m_memory.read((address / m_bytesPerBlock) * m_bytesPerBlock, m_bytesPerBlock);
			return data[index][offset];
		}

	}

}
