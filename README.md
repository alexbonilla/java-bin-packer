# java-bin-packer
Java Version of a bin-packer at https://github.com/jakesgordon/bin-packing/. jakesgordon made an awesome job explaining his code at http://codeincomplete.com/posts/2011/5/7/bin_packing/

I translated the forementioned code to Java, since I saw it as a simple way to balance virtual machines in different hosts. But that is a complete different story.

The code preserves the funcionality of the original javascript code, except that I added the option to handle more than 1 packet.

<h2>Demo</h2>
Clone the code 
<code>https://alexbonilla@github.com/alexbonilla/java-bin-packer.git</code>
com/iveloper/utils/packer/example/JavaBinPacker.java contains a running example you can play with.

<h2>Usage</h2>
<p>If you want to use it inside your project, you need to do something like this:</p>
<code>
        
        Packer packer = new Packer(2, 600, 800);// 2 available packets to fill, 600x800 each
        ArrayList<Node> blocks = new ArrayList();

        blocks.add(new Node("Figure1", 300, 400));
        blocks.add(new Node("Figure2", 300, 400));
        blocks.add(new Node("Figure3", 300, 400));
        blocks.add(new Node("Figure4", 300, 400));
        blocks.add(new Node("Figure5", 300, 400));
        blocks.add(new Node("Figure6", 300, 400));
        blocks.add(new Node("Figure7", 300, 400));
        blocks.add(new Node("Figure8", 300, 400));
        blocks.add(new Node("Figure9", 300, 400));

        Collections.sort(blocks, new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {

                return (Double.compare(b.w, a.w)); //doing the sort based on the width, you can change it accordingly to your needs.
            }
        });

        packer.fit(blocks);
        Iterator<Node> blocksItr = blocks.iterator();
        while (blocksItr.hasNext()) {
            Node block = blocksItr.next();
            if (block.fit != null) {
                if (block.fit.isroot) {
                    System.out.format("%32s", "Pack Starts Here");
                    System.out.println("");
                    System.out.format("%32s%24s%16s%16s%16s", "Display name", "x", "y", "w", "h");
                    System.out.println("");
                }
                System.out.format("%32s%24s%16s%16s%16s", block.name, block.fit.x, block.fit.y, block.w, block.h);
                System.out.println("");
            }
        }

        System.out.println("");
</code>

See source code comments for more details.

<h2>License</h2>
See <a href="https://github.com/alexbonilla/java-bin-packer/blob/master/LICENSE">LICENSE</a> file.

<h2>Contact</h2>
If you have any ideas, feedback, requests or bug reports, you can reach me at <a href="mailto:alex@iveloper.com">alex@iveloper.com</a>, or via my website: <a href="http://iveloper.com/">iveloper.com</a>
