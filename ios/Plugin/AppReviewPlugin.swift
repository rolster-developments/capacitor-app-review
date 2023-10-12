import Foundation
import Capacitor
import StoreKit

@objc(AppReviewPlugin)
public class AppReviewPlugin: CAPPlugin {
    @objc func requestReview(_ call: CAPPluginCall) {
        SKStoreReviewController.requestReview()
        call.resolve()
    }
    
    @objc func openStoreScreen(_ call: CAPPluginCall) {
        call.resolve()
    }
}
