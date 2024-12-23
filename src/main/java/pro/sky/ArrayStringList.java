package pro.sky;

import java.util.Arrays;

public class ArrayStringList implements StringList {
    private String[] list;
    private int size;
    private static final int DEFAULT_LENGTH = 8;

    public ArrayStringList(int size) {
        this.size = 0;
        list = new String[size];
    }

    public ArrayStringList() {
        this(ArrayStringList.DEFAULT_LENGTH);
    }

    @Override
    public String add(String item) {
        checkNewSize(size + 1);
        list[size] = item;
        size++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (index == size) {
            return add(item);
        } else {
            checkIndex(index);
            checkNewSize(size + 1);
            System.arraycopy(list, index, list, index + 1, size - index);
            list[index] = item;
            size++;
            return item;
        }
    }

    @Override
    public String set(int index, String item) {
        checkIndex(index);
        list[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        return remove(indexOf(item));
    }

    @Override
    public String remove(int index) {
        checkIndex(index);
        String res = list[index];
        System.arraycopy(list, index + 1, list, index, size - index);
        size--;
        return res;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) > -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (compareElements(item, list[i])) {
                return i;
            }
        }
        return -1;
    }

    private boolean compareElements(String a, String b) {
        return a == null && b == null || a != null && a.equals(b);
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (compareElements(item, list[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        checkIndex(index);
        return list[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new NullPointerException();
        } else if (this == otherList) {
            return true;
        } else if (otherList.size() != size) {
            return false;
        } else {
            for (int i = 0; i < size; i++) {
                if (!compareElements(otherList.get(i), get(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        list = new String[DEFAULT_LENGTH];
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(list, size);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void grow(int newSize) {
        String[] newList = new String[(int) (Math.max(list.length * 1.618, newSize))];
        System.arraycopy(list, 0, newList, 0, size);
        list = newList;
    }

    private void checkNewSize(int newSize) {
        if (newSize > list.length) {
            grow(newSize);
        }
    }
}
