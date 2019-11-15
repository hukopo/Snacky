const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");

module.exports = {
  entry: "./src/index.tsx",
  output: {
    path: path.join(__dirname, "./"),
    filename: "index-bundle.js"
  },
  devServer: {
      port: 8081,
      contentBase: "src/",
      historyApiFallback: true,
      proxy: {
          '/': {
              target: 'http://localhost:8080',
              pathRewrite: {
                  '^/': ''
              }
          }
      }
  },
  resolve: {
    extensions: [".ts", ".tsx", ".js", ".json"],
  },
  module: {
    rules: [
      {
        test: /\.(ts|js)x?$/,
        use: [
          {
            loader: "babel-loader",
            options: {
              plugins: ["@babel/plugin-transform-typescript"],
            },
          },
        ],
        include: [path.resolve(__dirname, "src")]
      },
      {
        test: /\.less$/,
        use: [
          "classnames-loader",
          "style-loader",
          {
            loader: "css-loader",
            options: {
              modules: {
                localIdentName: "[name]-[local]-[hash:base64:3]"
              }
            }
          },
          "less-loader"
        ],
        include: [path.resolve(__dirname, "src")]
      },
      {
        test: /\.css$/,
        use: ["style-loader", "css-loader"]
      }
    ]
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: "./src/index.html"
    })
  ]
};