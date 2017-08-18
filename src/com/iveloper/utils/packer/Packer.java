/*
 * Copyright (c) 2017, alexbonilla
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.iveloper.utils.packer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author alexbonilla
 */
public class Packer {

    private final ArrayList<Node> root = new ArrayList();

    public Packer(int numofpackets, double w, double h) {
        for(int i = 0; i< numofpackets;i++){
            this.root.add(new Node(0, 0, w, h));
        }        
    }
    

    public void fit(ArrayList<Node> blocks) {
        Node node;
        Node block;
        Iterator<Node> blockItr = blocks.iterator();
        int n=0;
        while (blockItr.hasNext()) {
            block = blockItr.next();
            if ((node = this.findNode(this.root.get(n), block.w, block.h))!=null) {
                block.fit = this.splitNode(node, block.w, block.h);
                if(node.isroot){
                    block.fit.isroot = true;
                }
            }else{
                n++;
            }
        }
    }

    public Node findNode(Node root, double w, double h) {
        if (root.used) {
            Node right = findNode(root.right, w, h);
            return (right != null ? right : findNode(root.down, w, h));
        } else if ((w <= root.w) && (h <= root.h)) {
            return root;
        } else {
            return null;
        }
    }

    public Node splitNode(Node node, double w, double h) {
        node.used = true;
        node.down = new Node(node.x, node.y + h, node.w, node.h - h);
        node.right = new Node(node.x + w, node.y, node.w - w, h);
        return node;
    }

}
