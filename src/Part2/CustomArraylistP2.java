package Part2;
import java.util.Arrays;

public class CustomArraylistP2<T>
{
    public Object[] data ;
    public static int Def_Size = 10;
    public int size = 0;

    public CustomArraylistP2()
    {
        this.data = new Object[Def_Size];
    }

    public void add(T num)
    {
        if (isFull())
        {
            resize();
        }
        data[size] = num;
        size++;
    }

    public void set(int index,T value)
    {
        if (data[index] != null)
        {
            data[index] = value;
        }
        else
        {
            throw new ArrayIndexOutOfBoundsException("Index "+index+" out of bounds!");
        }
    }

    public void remove(int index)
    {
        if (data[index] != null)
        {
            data[index] = null;
            for (int i = index; i < size; i++) {
                data[i] = data[i + 1];
            }
            size--;
        }
        else
        {
            throw new ArrayIndexOutOfBoundsException("Index "+index+" out of bounds!");
        }
    }

    public T get (int index)
    {
        return (T) (data[index]);
    }

    public int size()
    {
        return size;
    }

    public int indexOf(T o)
    {
        int index = -1;
        for (int i = 0; i < size; i++)
        {
            if(data[i].equals(o))
            {
                index = i;
            }
        }
        return index;
    }

    public boolean contains(T value)
    {
        boolean check = false;
        for (int i = 0; i < size; i++)
        {
            if(data[i].equals(value))
            {
                check = true;
            }
        }
        return check;
    }

    private void resize()
    {
        Object[] temp = new Object[data.length * 2];

        for (int i = 0; i < data.length; i++)
        {
            temp[i] =data[i];
        }
        data = temp;
    }

    private boolean isFull()
    {
        return size == data.length;
    }

    @Override
    public String toString()
    {
        return Arrays.toString(data);
    }
}
