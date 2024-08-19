import React, { useState } from "react";
import { IoMdClose } from "react-icons/io";

export const CartItem = ({
  product,
  cartitem,
  onQuantityChange,
  onRemoveFromCart,
}) => {
  const [quantity, setQuantity] = useState(cartitem.quantity);
  console.log(cartitem);

  const handleDecreaseQuantity = async () => {
    if (quantity > 1) {
      await onQuantityChange(cartitem.cartItemId, product.productId, -1);
      setQuantity((prev) => prev - 1);
    }
  };

  const handleIncreaseQuantity = async () => {
    await onQuantityChange(cartitem.cartItemId, product.productId, 1);
    setQuantity((prev) => prev + 1);
  };

  const handleRemoveFromCart = async () => {
    await onRemoveFromCart(cartitem.cartItemId, product.productId);
  };

  return (
    <div>
      <div className="rounded-lg">
        <div className="justify-between mb-6 rounded-lg bg-white p-6 shadow-md sm:flex sm:justify-start">
          <img
            src={product.imgUrl}
            alt="product-image"
            className="w-full h-28 rounded-lg sm:w-40"
          />
          <div className="sm:ml-4 sm:flex sm:w-full sm:justify-between">
            <div className="mt-5 sm:mt-0">
              <h2 className="text-lg font-bold text-gray-900">
                {product.productName}
              </h2>
              <p className="mt-1 text-sm text-gray-700">
                Price: ₹{product.price}
              </p>
              <p className="mt-1 text-sm text-gray-700">
                Author: {product.vendorName}
              </p>
            </div>
            <div className="mt-4 flex justify-between sm:space-y-6 sm:mt-0 sm:block sm:space-x-6">
              <div className="flex items-center border-gray-100">
                <span
                  className="cursor-pointer rounded-l bg-gray-100 py-1 px-3.5 duration-100 hover:bg-blue-500 hover:text-blue-50"
                  onClick={() => handleDecreaseQuantity()}
                >
                  {" "}
                  -{" "}
                </span>
                <input
                  className="h-8 w-8 border bg-white text-center text-xs outline-none"
                  type="number"
                  value={quantity}
                  min="1"
                  readOnly
                />
                <span
                  className="cursor-pointer rounded-r bg-gray-100 py-1 px-3 duration-100 hover:bg-blue-500 hover:text-blue-50"
                  onClick={() => handleIncreaseQuantity()}
                >
                  +
                </span>
              </div>
              <div className="flex items-center space-x-4">
                <p className="text-sm">
                  ₹{(product.price * quantity).toFixed(2)}
                </p>
                {/* <button
                  className="lws-removeFromCart"
                  onClick={() => handleRemoveFromCart()}
                >
                  <IoMdClose />
                </button> */}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
