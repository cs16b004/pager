package pager

import io.circe.Json

import scala.collection.mutable.ArrayBuffer


class DocTree(config:Json,nodeData:DocTreeNode) {
  private var children:ArrayBuffer[DocTree] = new ArrayBuffer[DocTree]()

  def addChild(node:DocTreeNode):Boolean = {
    children += new DocTree(config, node)
    true
  }

}
